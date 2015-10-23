package ax.makila.rushkeeper.experiment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.exceptions.OAuthException;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

import com.microsoft.hsg.Authenticator;

public class HealthVaultAPI extends DefaultApi20 {
	
	//TRIAL AND ERROR

//	private static final String AUTHORIZE_URL = "https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=%s&redirect_uri=%s";
    private static final String AUTHORIZE_URL = "https://account.healthvault-ppe.co.uk/redirect.aspx?target=AUTH&targetqs=appid%3D8ee9e225-37be-4afe-b018-d08ef48e1ab2";
	
	private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
	

	@Override
	public String getAccessTokenEndpoint() {
		return "https://account.healthvault-ppe.co.uk/redirect.aspx?target=AUTH&targetqs=appid%3D8ee9e225-37be-4afe-b018-d08ef48e1ab2";
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		return String.format(AUTHORIZE_URL, config.getApiKey(),
				OAuthEncoder.encode(config.getCallback()));
	}
	
	@Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new AccessTokenExtractor() {
            
            @Override
            public Token extract(String response) {
                Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");
 
                Matcher matcher = Pattern.compile("\"access_token\" : \"([^&\"]+)\"").matcher(response);
                if (matcher.find())
                {
                  String token = OAuthEncoder.decode(matcher.group(1));
                  return new Token(token, "", response);
                } 
                else
                {
                  throw new OAuthException("Response body is incorrect. Can't extract a token from this: '" + response + "'", null);
                }
            }
        };
    }
	
	@Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }
	
	public void test() {
//		Authenticator abc = new A
	}
    
}
