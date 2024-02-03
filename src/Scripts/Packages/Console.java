package Scripts.Packages;

import Scripts.Functions.Log;

public class Console extends BasePackage{
    public Console(){
        super();
        childFunctions.add(new Log());
    }
}
