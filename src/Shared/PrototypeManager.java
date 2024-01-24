package Shared;

import java.util.ArrayList;

import Prototypes.Prototype;

public class PrototypeManager {
    public ArrayList<Prototype> prototypes;

    public PrototypeManager(String path) {

    }

    public Prototype searchPrototypeById(String id) {
        for (Prototype pr : prototypes) {
            if (pr.id == id) {
                return pr;
            }
        }
        return null;
    }

    private boolean getIsParent(Prototype child, Prototype parent) {
        if (child.parent == null) {
            return false;
        }
        if (child.parent == parent) {
            return true;
        }
        return getIsParent(child.parent, parent);
    }

    public Prototype searchPrototypeById(String id, String parentUnavailable) {
        for (Prototype pr : prototypes) {
            if (pr.id == id && !getIsParent(pr, searchPrototypeById(parentUnavailable))) {
                return pr;
            }
        }
        return null;
    }
}
