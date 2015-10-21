package ax.makila.rushkeeper.experiment;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.server.VaadinPortlet.VaadinHttpAndPortletRequest;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletService;

public class LoginWindow extends HttpServlet {

	public LoginWindow() {
		HttpServletRequest request = VaadinServletService.getCurrentServletRequest();
		HttpServletResponse response = VaadinServletService.getCurrentResponse();
		
		try {
			ShellUtils.login(request, response, 
				request.getContextPath() + request.getServletPath() + request.getPathInfo() + "?" +
					(request.getQueryString() == null ? "" : request.getQueryString()));
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ShellUtils.login(, response, returnUrl);
		
//		service = HealthVaultApp.getInstance(this);
//		if (service.isAppConnected()) {
//			service.start(this, this);
//		}
//
//		hvClient = new HealthVaultClient();
	}
	
	
//	private static final String AUTH_URL = "https://account.healthvault-ppe.co.uk";
//	private static final String TARGET = "APPAUTH";
//	private static final String APPID = "appid=8ee9e225-37be-4afe-b018-d08ef48e1ab2";
//	
//	private Link gplusLoginButton;
//
//    OAuthService service;
////
////    @Inject
////    UserSession userSession;
////    final String redirectUrl;
//    @Override
//    public void attach() {
//        super.attach();
//
//        service = createService();
//        String url = service.getAuthorizationUrl(null);
//        
//        gplusLoginButton = new Link("Login with Google", new ExternalResource(url));
//        gplusLoginButton.addStyleName(ValoTheme.LINK_LARGE);
//
//        VaadinSession.getCurrent().addRequestHandler(this);
//
//        setContent(new VerticalLayout(gplusLoginButton).alignAll(
//                Alignment.MIDDLE_CENTER).withFullHeight());
//        setModal(true);
//        setWidth("300px");
//        setHeight("200px");
//    }
//    
//    private OAuthService createService() {
//        ServiceBuilder sb = new ServiceBuilder();
//        sb.provider(api);
//        sb.apiKey(gpluskey);
//        sb.apiSecret(gplussecret);
//        sb.scope("email");
//        String callBackUrl = Page.getCurrent().getLocation().toString();
//        if(callBackUrl.contains("#")) {
//            callBackUrl = callBackUrl.substring(0, callBackUrl.indexOf("#"));
//        }
//        sb.callback(callBackUrl);
//        return sb.build();
//    }
//    
//	@Override
//	public boolean handleRequest(VaadinSession session, VaadinRequest request,
//	        VaadinResponse response) throws IOException {
//	    if (request.getParameter("code") != null) {
//	        String code = request.getParameter("code");
//	        Verifier v = new Verifier(code);
//	        Token t = service.getAccessToken(null, v);
//
//	        OAuthRequest r = new OAuthRequest(Verb.GET,
//	                "https://www.googleapis.com/plus/v1/people/me");
//	        service.signRequest(t, r);
//	        Response resp = r.send();
//
//	        GooglePlusAnswer answer = new Gson().fromJson(resp.getBody(),
//	                GooglePlusAnswer.class);
//
//	        userSession.login(answer.emails[0].value, answer.displayName);
//
//	        close();
//	        VaadinSession.getCurrent().removeRequestHandler(this);
//
//	        ((VaadinServletResponse) response).getHttpServletResponse().
//	                sendRedirect(redirectUrl);
//	        return true;
//	    }
//
//	    return false;
//	}
}
