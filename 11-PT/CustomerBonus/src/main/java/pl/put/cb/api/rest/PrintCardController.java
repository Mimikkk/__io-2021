package pl.put.cb.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.put.cb.dao.CustomersDAO;
import pl.put.cb.model.Customer;
import pl.put.cb.print.CardPrinterAPI;


@RestController
@RequestMapping("/print_card")
public class PrintCardController {

    private static final Logger logger = LoggerFactory.getLogger(PrintCardController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(value="id") Integer id) {

        Customer customer = CustomersDAO.Instance.ReadById(id);
        if (customer == null) return "No such customer...";

        CardPrinterAPI.Instance.printCard(customer.toString());

        return "Printed: " + customer;
    }

}


