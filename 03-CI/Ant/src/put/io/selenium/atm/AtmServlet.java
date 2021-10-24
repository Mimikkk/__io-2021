package put.io.selenium.atm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("serial")
public class AtmServlet extends HttpServlet {
	private Configuration tmplCfg;

	private final boolean testMode;
	
	public AtmServlet(boolean testMode) {
		this.testMode = testMode;
		tmplCfg = new Configuration();
		ClassTemplateLoader loader = new ClassTemplateLoader(this.getClass(), "/pages");
		tmplCfg.setTemplateLoader(loader);
		tmplCfg.setDefaultEncoding("UTF-8");
		tmplCfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);

		final String tmplName;
		Map<String,Object> data = new HashMap<String, Object>();
		
		HttpSession sess = request.getSession(true);
		AtmCardInfo atmCard = (AtmCardInfo) sess.getAttribute("atm_card_info");
		if (atmCard == null) {
			atmCard = new AtmCardInfo();
			tmplName = "init";
		} else {
			final String action = (String) request.getPathInfo().replaceFirst("^/", "");
			
			// for tests only!
			if (testMode && "test_only_reset_all".equals(action)) {
				atmCard = new AtmCardInfo();
				tmplName = "init";
			} else {
				tmplName = serveRequest(action, request, response, atmCard, data);
			}
			data.put("pin_tries_left", atmCard.pinTriesLeft);
		}
		sess.setAttribute("atm_card_info", atmCard);
		
		Template tmpl = tmplCfg.getTemplate(tmplName);
		try {
			tmpl.process(data, response.getWriter());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	private String serveRequest(String action, HttpServletRequest request, HttpServletResponse response, AtmCardInfo atmCard, Map<String, Object> data) {
		if ("init".equals(action)) {
			return "init";
		} else if ("card_in".equals(action)) {
			if (atmCard.isCardLocked) {
				return "card_locked";
			} else {
				atmCard.cardIn();
				return "enter_pin";
			}
		} else if ("enter_pin".equals(action)) {
			final String pin = (String) request.getParameter("pin");
			if (atmCard.tryPin(pin)) {
				return "choose_op";
			} else {
				if (atmCard.isCardLocked) {
					return "wrong_pin_card_locked";
				} else {
					return "wrong_pin";
				}
			}
		} else if ("card_out".equals(action)) {
			atmCard.cardOut();
			return "init";
		} else if ("op_withdraw".equals(action)) {
			return "enter_withdrawal_amount";
		} else if ("withdraw_amount".equals(action)) {
			final String amount = (String) request.getParameter("amount");
			String error = atmCard.tryWithdraw(amount);
			if (error != null) {
				data.put("error_msg", error);
				return "enter_withdrawal_amount";
			} else {
				return "ask_take_card";
			}
		} else if ("withdraw_take_card".equals(action)) {
			return "ask_take_money";
		} else if ("withdraw_take_money".equals(action)) {
			return "thank_you";
		} else {
			return "init";
		}
	}
}
