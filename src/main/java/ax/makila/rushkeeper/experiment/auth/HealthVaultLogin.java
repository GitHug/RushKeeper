package ax.makila.rushkeeper.experiment.auth;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinService;

public class HealthVaultLogin {
	
	// TODO 
	public void login() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		VaadinResponse response = VaadinService.getCurrentResponse();
		
//		try {
//			try {
//                VaadinSession session = VaadinSession.getCurrent();
//                PersonInfo personInfo = (PersonInfo)session.getAttribute(HealthVaultConstants.PERSON_INFO_KEY);
//                RequestCtx.setPersonInfo(personInfo);
//
//				if (personInfo == null) {
//					ShellUtils.login(request, response, 
//						request.getContextPath() + request.getServletPath() + request.getPathInfo() + "?" +
//							(request.get == null
//										? "" 
//										: request.getContextPath()()));
//					}
//				}
//				
//				showView(request, response, handler.processRequest(request, response));
//				
//			} catch (HVAccessDeniedException axdenied) {
//				ShellUtils.login(request, response, "things/home");
//			} catch (Exception e) {
//				throw new ServletException(e);
//			}
//		} finally {
//			RequestCtx.setPersonInfo(null);
//		}
	}

}
