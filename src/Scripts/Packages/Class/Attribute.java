package Scripts.Packages.Class;

import Scripts.Functions.Class.Attribute.*;
import Scripts.Packages.BasePackage;

public class Attribute extends BasePackage{
    public Attribute(){
        super();
        this.childFunctions.add(new Const());
        this.childFunctions.add(new Typed());
        this.childFunctions.add(new Any());
    }
}
