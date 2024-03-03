package Scripts.Packages.Object;

import Scripts.Functions.Object.Attribute.*;
import Scripts.Packages.BasePackage;

public class Attribute extends BasePackage{
    public Attribute(){
        super();
        childFunctions.add(new Any());
        childFunctions.add(new Typed());
        childFunctions.add(new Const());
    }
}
