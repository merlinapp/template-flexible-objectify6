package com.merlinjobs.flexible.template;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.merlinjobs.flexible.template.Item;

/**
 * Created by wolfblue on 03/01/2017.
 */

public class OfyService {

    static
    {
        ObjectifyService.init();
        ObjectifyService.register(Item.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
    public static ObjectifyFactory factory() { return ObjectifyService.factory(); }

}
