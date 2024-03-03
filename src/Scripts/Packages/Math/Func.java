package Scripts.Packages.Math;

import Scripts.Functions.Math.Func.*;
import Scripts.Packages.BasePackage;

public class Func extends BasePackage {
    public Func(){
        super();
        this.childFunctions.add(new Abs());
        this.childFunctions.add(new Round());
        this.childFunctions.add(new Ceil());
        this.childFunctions.add(new Floor());
    }
}
