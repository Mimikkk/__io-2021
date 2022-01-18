package pl.put.cb.api.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/status")
public class StatusCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(StatusCheckerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "text/plain")
    public String get() {
        return "CustomerBonus application is running...";
    }




}


