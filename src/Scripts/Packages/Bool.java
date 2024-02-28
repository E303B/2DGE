package Scripts.Packages;

import Scripts.Functions.Bool.*;

public class Bool extends BasePackage{
    public Bool(){
        super();
        this.childFunctions.add(new Equal());
        this.childFunctions.add(new StrongEqual());
        this.childFunctions.add(new Greater());
        this.childFunctions.add(new Lesser());
        this.childFunctions.add(new Not());
        this.childFunctions.add(new And());
        this.childFunctions.add(new Or());
    }
}
