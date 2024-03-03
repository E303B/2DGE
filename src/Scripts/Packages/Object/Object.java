package Scripts.Packages.Object;

import Scripts.Functions.Object.*;
import Scripts.Packages.BasePackage;

public class Object extends BasePackage {
    public Object(){
        super();
        childFunctions.add(new New());
        childFunctions.add(new Set());
        childFunctions.add(new Get());
        childFunctions.add(new Length());
        childPackages.add(new Attribute());
    }
}
