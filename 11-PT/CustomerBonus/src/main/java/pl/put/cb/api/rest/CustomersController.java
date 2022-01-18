package pl.put.cb.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.put.cb.dao.CustomersDAO;
import pl.put.cb.model.Customer;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomersController {

    private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Integer> get(@RequestParam(value="spendings", defaultValue="1000.0") Double spendings) {
        List<Integer> customerIds = new ArrayList<>();

        for (Customer customer : CustomersDAO.ReadAll()){
            if (customer.Spendings() >= spendings){
                customerIds.add(customer.Id());
                logger.debug(customer.Id()+"");
            }
        }
        return customerIds;
    }

}


