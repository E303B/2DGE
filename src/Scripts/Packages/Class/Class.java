package Scripts.Packages.Class;

import Scripts.Functions.Class.*;
import Scripts.Packages.BasePackage;

public class Class extends BasePackage {
    public Class(){
        super();
        this.childPackages.add(new Attribute());

        this.childFunctions.add(new New());
        this.childFunctions.add(new Get());
        this.childFunctions.add(new Set());
    }
}
