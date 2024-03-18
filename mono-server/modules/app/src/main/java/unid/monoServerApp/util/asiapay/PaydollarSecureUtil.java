package unid.monoServerApp.util.asiapay;

public class PaydollarSecureUtil {

	public static synchronized String generatePaymentSecureHash(
			String merchantId, String orderRef, String currCode, String amount,
			String paymentType) throws PaydollarSecureException {
		PaydollarSecure paydollarSecure = PaydollarSecureFactory
				.getPaydollarSecure("SHA");

		String secureHash = null;

		String secureHashSecret = paydollarSecure.loadSecureHashSecret();

		secureHash = paydollarSecure.generatePaymentSecureHash(merchantId,
				orderRef, currCode, amount, paymentType, secureHashSecret);

		return secureHash;

	}

	public static synchronized boolean verifyPaymentDatafeed(String src,
			String prc, String successCode, String ref, String payRef,
			String cur, String amt, String payerAuth, String[] secureHashs) {

		PaydollarSecure paydollarSecure = PaydollarSecureFactory
				.getPaydollarSecure("SHA");
		String secureHashSecret = paydollarSecure.loadSecureHashSecret();
		boolean verifyResult = false;

		for (int i = 0; secureHashs != null && i < secureHashs.length; i++) {

			verifyResult = paydollarSecure.verifyPaymentDatafeed(src, prc,
					successCode, ref, payRef, cur, amt, payerAuth,
					secureHashSecret, secureHashs[i]);

			if (verifyResult) {
				break;
			}
		}

		System.out.println("verifyResult =" + verifyResult);
		return verifyResult;

	}

}
