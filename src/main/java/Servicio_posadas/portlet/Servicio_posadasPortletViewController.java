package Servicio_posadas.portlet;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author liferay
 */
@Controller
@RequestMapping("VIEW")
public class Servicio_posadasPortletViewController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		getXML();
		message("SiteId"+getSiteId(request));
		return "view";
	}
	
	public void getXML(){
		message("<----- Metodo getXML ------>");
		List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles();
		int count = 0;
		for(JournalArticle article: articles){
			message("Struture: ".concat(article.getDDMStructureKey()));
			message("Description: ".concat(article.getDescription()));
			message("ArticleID: ".concat(article.getArticleId()));
			count++;
			if(count==3)
				break;
			
		}
	}
	public Long getSiteId(RenderRequest resourceRequest){
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long id = themeDisplay.getLayout().getGroupId();
		return id;
	}
	
	public void message(String message){
		System.out.println(message); 
	}
}