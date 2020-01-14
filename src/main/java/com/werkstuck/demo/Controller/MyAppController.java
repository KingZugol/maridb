package com.werkstuck.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
   @PostMapping("/posts/{name}")
    public String pPosts(Model model, @RequestBody String payload, @PathVariable("name") String name) throws JsonProcessingException {
        Post newPost = new ObjectMapper().readValue(payload, Post.class);
        prep.save(newPost);
        model.addAttribute("post", newPost);
        return "redirect:/name/" + name;
    }

@GetMapping("/effects")
    public String getEffects(Model model){
    String url = "http://strainapi.evanbusse.com/7wvDuw5/searchdata/effects";
    Effects[] weedByEffect = restTemplate.getForObject(url, Effects[].class);
    model.addAttribute("weedByEffect", weedByEffect);
    return "fragments/Effects";
}

@GetMapping("/effects/{effect}")
public String getWeedByEffects(Model model, @PathVariable("effect") String effect) throws UnsupportedEncodingException {
    effect.toLowerCase();
    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/effect/" + effect;
    WeedByEffect[] weedByEffects = restTemplate.getForObject(url, WeedByEffect[].class);
    model.addAttribute("weedByEffects", weedByEffects);
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
    public String getWeedByFlavor(Model model, @PathVariable("flavor") String flavor) {

    String url ="http://strainapi.evanbusse.com/7wvDuw5/strains/search/flavor/" + flavor;
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
    String url ="http://strainapi.evanbusse.com/7wvDuw5/strains/search/race/" + species;
    Weed[] weedBySpecies = restTemplate.getForObject(url, Weed[].class);
    model.addAttribute("weed", weedBySpecies);
    return "fragments/WeedBySpecies";
    }

@GetMapping("/name")
    public String getNameView(){
    return "fragments/NameView";
}

@GetMapping("/name/{name}")
public String getWeedComplete(@PathVariable("name") String name, Model model){
    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/name/" + name;
    WeedCompleteObject[] weedBySearch = restTemplate.getForObject(url, WeedCompleteObject[].class);
    WeedCompleteObject weedComplete = weedBySearch[0];

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/effects/" + weedComplete.getId();
    System.out.println(url);
    WeedEffectsForName effect = restTemplate.getForObject(url, WeedEffectsForName.class);
    weedComplete.setEffects(effect);

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/flavors/" + weedComplete.getId();
    String[] flavor = restTemplate.getForObject(url, String[].class);
    weedComplete.setFlavors(flavor);
    model.addAttribute("allPost",prep.findByWeedId(weedComplete.getId()));
    model.addAttribute("weedBySearch", weedComplete);

    return "fragments/WeedComplete";
}
/* Problem: Bei manchen Namen kommen mehrere Ergebnisse zurück, zBsp.: Alaska liefert alle Grasssorten die Alaska in ihrem Namen haben. Brauchen Lösungsansatz
*  Idee: Bei mehreren rückgabe alle mit Namen listen und dann da zur konkreten Produkt Seite gehen */
@PostMapping("/name")
public String getNameSearchResults(@RequestBody String searchString, Model model){

    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/name/" + searchString;
    WeedCompleteObject[] weedBySearch = restTemplate.getForObject(url, WeedCompleteObject[].class);
    WeedCompleteObject weedComplete = weedBySearch[0];

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/effects/" + weedComplete.getId();
    System.out.println(url);
    WeedEffectsForName effect = restTemplate.getForObject(url, WeedEffectsForName.class);
    weedComplete.setEffects(effect);

    url = "http://strainapi.evanbusse.com/7wvDuw5/strains/data/flavors/" + weedComplete.getId();
    String[] flavor = restTemplate.getForObject(url, String[].class);
    weedComplete.setFlavors(flavor);
    model.addAttribute("weedBySearch", weedComplete);

    model.addAttribute("allPost",prep.findByWeedId(weedComplete.getId()));
    return "fragments/WeedComplete";
}
}

