package Scripts.Packages;

import Scripts.Functions.Math.Func.*;

public class Func extends BasePackage {
    public Func(){
        super();
        this.childFunctions.add(new Abs());
        this.childFunctions.add(new Round());
        this.childFunctions.add(new Ceil());
        this.childFunctions.add(new Floor());
    }
}
