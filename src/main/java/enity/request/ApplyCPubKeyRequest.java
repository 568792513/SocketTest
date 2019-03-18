package enity.request;

public class ApplyCPubKeyRequest extends CommonRequest {

    //    Ëø¾ß¹«Ô¿
    private String PublicKey;

    public String getPublicKey() {
        return PublicKey;
    }

    public void setPublicKey(String publicKey) {
        PublicKey = publicKey;
    }
}
