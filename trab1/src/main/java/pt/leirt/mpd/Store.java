package pt.leirt.mpd;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.leirt.mpd.products.Electronics;
import pt.leirt.mpd.products.Speaker;
import pt.leirt.mpd.products.TV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Store {
    private Collection<Electronics> catalog = new ArrayList<>();

    public Store addCatalog(Electronics product) {
        catalog.add(product);
        return this;
    }

    // query methods

    public Iterable<Electronics> fromSamsung() {
        List<Electronics> samsungProds = new ArrayList<>();
        var samsungProds1 = new ArrayList<Electronics>();

        for(var p : catalog) {
            if (p.getBrand().equalsIgnoreCase("Samsung"))
                samsungProds.add(p);
        }
        return samsungProds;
    }

    public Iterable<TV> getAbove65Inches() {
        // TO IMPLEMENT
        return null;
    }

    public Iterable<Speaker> getSpeakersInPowerInterval() {
        // TO IMPLEMENT
        return null;
    }

    public Electronics getMostExpensiveCommunicationDevice() {
        // TO IMPLEMENT
        return null;
    }






}
