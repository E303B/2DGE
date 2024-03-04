package Scripts.Packages;

import Scripts.Functions.Func.*;

public class Func extends BasePackage {
    public Func(){
        super();
        childFunctions.add(new New());
        childFunctions.add(new Call());
    }
}
