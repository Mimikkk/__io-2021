package pl.put.cb.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * Represents naive implementation of the card-printing API.
 */
public class CardPrinterAPI {

  private static final int NUMBER_OF_PRINTERS = 50;

  public static final CardPrinterAPI Instance = new CardPrinterAPI();

  private final Logger logger;

  private final Semaphore printers_pool;

  private CardPrinterAPI() {
    printers_pool = new Semaphore(NUMBER_OF_PRINTERS);
    logger = LoggerFactory.getLogger(CardPrinterAPI.class);
  }

  public void printCard(String text) {
    try {
      printers_pool.acquire();
      logger.debug("printing... " + text);
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      logger.debug(e.getLocalizedMessage());
    } finally {
      printers_pool.release();
    }
  }

  public int getAvailablePrinters() {
    return printers_pool.availablePermits();
  }

}
