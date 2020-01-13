package com.werkstuck.demo.Controller;

import com.werkstuck.demo.Data.postDAO;
import com.werkstuck.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class MyAppController {
RestTemplate restTemplate = new RestTemplate();
@Autowired
public UserRepository repository;
private User user = new User("test", "password");
    @Autowired
    public postDAO prep;



    @GetMapping("/login")
public String getLoginView(){

    return "fragments/LoginTemp.html";
}
@PostMapping("/login")
public String postLogin(Model model,HttpServletRequest request, @RequestParam ("username") String username, @RequestParam ("pwd") String pwd){
   User loginUser = repository.findByUsername(username);
    if(loginUser.getUsername().equals(username) && loginUser.getPassword().equals(pwd)){
    HttpSession session = request.getSession();
        session.setAttribute("name", username);
        session.setMaxInactiveInterval(30*60);
        Cookie userName = new Cookie("user", loginUser.getUsername());
        userName.setMaxAge(30*60);
        model.addAttribute("name", username);
        return "fragments/LoginSuccess";
}
    else return "fragments/LoginTemp";
}
@GetMapping("/register")
public String getRegisterView(){
    return "fragments/RegisterView";
}
@PostMapping("/register")
public String postRegister(HttpServletRequest request, @RequestParam ("username") String username, @RequestParam ("pwd") String pwd){
    User newUser = new User(username, pwd);
    repository.save(newUser);
    System.out.println();
    return "fragments/LoginTemp";

}
    @GetMapping("/posts")

    public String getPosts(Model model) {
    model.addAttribute("allPost",prep.findAll());
    return "fragments/PostsView";
   }
   @PostMapping("/posts")
   public String pPosts(Model model,   @ModelAttribute ("Post") Post post) {
      Post newPost = new Post(post.getTitle(), post.getBody(), null);
      prep.save(newPost);
      model.addAttribute("post", newPost);
      return "redirect:/posts";
    }

@GetMapping("/effects")
    public String getEffects(Model model){
     String url = "http://strainapi.evanbusse.com/7wvDuw5/searchdata/effects";
    WeedByEffect[] weedByEffect = restTemplate.getForObject(url, WeedByEffect[].class);
    model.addAttribute("weedByEffect", weedByEffect);
    return "fragments/WeedByEffects";
}
@GetMapping("/flavors")
    public String getFlavours(Model model){
    String url = "http://strainapi.evanbusse.com/7wvDuw5/searchdata/flavors";
    String[] flavors = restTemplate.getForObject(url, String[].class);
    model.addAttribute("flavors", flavors);
    return "fragments/Flavors";

}

@GetMapping("/flavors/{flavor}")
    public String getWeedByFlavor(Model model, @PathVariable("flavor") String flavor) throws UnsupportedEncodingException {
    flavor.toLowerCase();
    String url ="http://strainapi.evanbusse.com/7wvDuw5/strains/search/flavor/" + URLEncoder.encode(flavor, StandardCharsets.UTF_8.toString());
    WeedByFlavor[] weedByFlavors = restTemplate.getForObject(url, WeedByFlavor[].class);
    model.addAttribute("WeedByFlavors", weedByFlavors);
    return "fragments/WeedByFlavor";

}
@GetMapping("/species")
    public String getSpecies(){
        return "fragments/Species";
    }


@GetMapping("/species/{type}")
public String getBySpecies(@PathVariable ("type") String species, Model model){
    species.toLowerCase();
    String url ="http://strainapi.evanbusse.com/7wvDuw5/strains/search/race/" + species;
    Weed[] weedBySpecies = restTemplate.getForObject(url, Weed[].class);
    model.addAttribute("weed", weedBySpecies);
    return "fragments/WeedBySpecies";
    }

@GetMapping("/name")
    public String getNameView(){
    return "fragments/NameView";
}
@PostMapping("/name")
    public String getNameSearchResults(@RequestBody String searchString, Model model) throws UnsupportedEncodingException {
    String search = searchString;
    URLEncoder.encode(search, StandardCharsets.UTF_8.toString());
    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/name/" + search;
    WeedCompleteObject[] weedBySearch = restTemplate.getForObject(url, WeedCompleteObject[].class);

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/effects/" + weedBySearch[0].getId();
    WeedEffectsForName effect = restTemplate.getForObject(url, WeedEffectsForName.class);
    weedBySearch[0].setEffects(effect);

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/flavors/" + weedBySearch[0].getId();
    String[] flavor = restTemplate.getForObject(url, String[].class);
    weedBySearch[0].setFlavors(flavor);
    model.addAttribute("weedBySearch", weedBySearch);
    return "fragments/WeedComplete";
}
}

