package com.midominio.evaluable2.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String homeHandler(Model m) {
		m.addAttribute("titulo", "Inicio");
		m.addAttribute("tituloH1", "Lorem ipsum dolor sit amet.");
		m.addAttribute("p1", "Duis enim tortor, placerat interdum ex ac, mattis facilisis orci. Aenean viverra diam ut mauris vulputate tempus. Integer vestibulum vehicula mollis. Aenean vitae ultricies metus. Aliquam commodo lectus in sodales eleifend. Etiam dapibus ullamcorper magna, non fermentum risus imperdiet nec. Morbi at sagittis diam. Morbi in ullamcorper ex. Aliquam eleifend maximus elit, sit amet finibus sapien ullamcorper sed. Fusce malesuada lobortis eleifend. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut blandit felis ac lobortis ultricies. Pellentesque eget est quis lectus ornare bibendum.");
		m.addAttribute("p2", "Ut nec est neque. Aliquam et erat vitae lectus pellentesque euismod. Suspendisse interdum sem ac magna imperdiet feugiat. Nullam gravida, libero sit amet egestas imperdiet, urna est aliquam lectus, eget tempor turpis enim a quam. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quis massa est. Nullam dapibus scelerisque lorem at lobortis. Sed eu scelerisque sapien. Nulla sem justo, volutpat in cursus ac, blandit eget augue. In non lacus vitae diam gravida accumsan at nec enim. Fusce in posuere dui. Vestibulum eleifend massa tortor, at blandit urna molestie nec. Aliquam a est condimentum, egestas lorem et, scelerisque ipsum. Sed vehicula urna urna, quis venenatis quam condimentum quis.");
		m.addAttribute("p3", "Quisque ullamcorper fringilla mauris, id faucibus nisi vulputate eget. Duis aliquet mi a enim tempor lobortis. Praesent tellus nisi, aliquet nec vulputate sit amet, dictum sit amet dolor. Nullam purus orci, pretium eget sagittis a, rhoncus eget nisi. Proin bibendum nulla non tincidunt varius. Praesent porta arcu pretium mauris fermentum malesuada. Quisque non est ut tellus vestibulum sagittis id vitae diam. Phasellus auctor suscipit est, non condimentum leo eleifend aliquet. Duis fermentum velit id mi congue tristique. Proin nec justo vehicula, fermentum risus at, laoreet elit. Praesent faucibus, odio luctus ornare tempus, augue risus euismod augue, non aliquam sapien turpis et urna. Praesent luctus convallis euismod. Sed semper nisl et purus mattis, sollicitudin gravida turpis lacinia. Morbi sed placerat nibh. Duis sed ante dui. Nunc luctus, libero imperdiet tempus hendrerit, augue nulla dignissim sem, eget pretium sapien nulla id felis.");
		return "home";
	}
}
