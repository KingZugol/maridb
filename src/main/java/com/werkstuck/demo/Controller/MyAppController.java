package com.werkstuck.demo.Controller;

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
    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/effect/" + URLEncoder.encode(effect,StandardCharsets.UTF_8.toString());
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
@GetMapping("/name/{name}")
public String getWeedComplete(@PathVariable("name") String name, Model model) throws UnsupportedEncodingException {
    URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
    String url = "http://strainapi.evanbusse.com/7wvDuw5/strains/search/name/" + name;
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

