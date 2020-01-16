package com.werkstuck.demo.Controller;

import com.werkstuck.demo.Data.postDAO;
import com.werkstuck.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;

/* todo: ErrorController mit thymeleaf,
     JS auf eventhandler ändern
     CSS fertig machen
     präsentation
     CustomHeader implementieren
        */
@Controller
public class ViewController {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    public postDAO prep;


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

