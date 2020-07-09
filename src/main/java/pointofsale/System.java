package pointofsale;

import com.sun.net.httpserver.Authenticator;

import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception { }
class Modem {
  public static void dialModem(int number) throws ModemDidNotConnectException {
  }
}

class RetryCCLaterException extends Exception {
  public RetryCCLaterException() {
  }

  public RetryCCLaterException(String message) {
    super(message);
  }

  public RetryCCLaterException(String message, Throwable cause) {
    super(message, cause);
  }

  public RetryCCLaterException(Throwable cause) {
    super(cause);
  }

  public RetryCCLaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

public class System {
  public static void main(String[] args) {

  }

  public static boolean USE_MODEM = false;

  public void authorizeCard(int amount, int number)
      throws RetryCCLaterException {
//      throws ModemDidNotConnectException, IOException {
    int retryCount = 3;
    boolean success = false;
    while (!success && retryCount > 0) {
      retryCount--;
      // dial modem
      try {
        if (USE_MODEM) {
          Modem.dialModem(123456789);
          success = true;
        } else {
          Socket s = new Socket("127.0.0.1", 8000);
          success = true;
        }
        // do the important stuff
      } catch (ModemDidNotConnectException | IOException e) {
        if (retryCount == 0) throw new RetryCCLaterException(e);
      }

//      } catch (ModemDidNotConnectException | IOException e) {
//        if (retryCount == 0) throw e;
//      } catch (IOException ioe) {
//        if (retryCount == 0) throw ioe;
//      }
    }
  }

  public void buy(int amount) {
    try {
      authorizeCard(amount, 1234);
    } catch (RetryCCLaterException e) {
      // useful human intervention;
    }
  }
}
// Good design MINIMIZES THE CONSEQUENCES OF CHANGE