package pl.put.cb.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.cb.dao.CustomersDAO;


@SpringBootApplication(scanBasePackages = {"pl.put.cb.api.rest"})
public class CustomerBonusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerBonusApplication.class, args);
    }
}
