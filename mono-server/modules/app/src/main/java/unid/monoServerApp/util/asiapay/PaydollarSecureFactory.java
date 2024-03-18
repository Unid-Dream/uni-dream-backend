package unid.monoServerApp.util.asiapay;

public class PaydollarSecureFactory {
	public static PaydollarSecure getPaydollarSecure(String secureType) {
		if ("SHA".equals(secureType))
			return new SHAPaydollarSecure();
		else
			return new SHAPaydollarSecure();
	}
}
