package utilities;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class PaymentTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL("https://demo2.2c2p.com/2C2PFrontend/PaymentAction/2.0/action");
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-Type", "text/plain");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
