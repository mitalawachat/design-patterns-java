class BackendDeveloper {
    public void codeJava() {
        System.out.println("I am coding java!");
    }
}

class FrontendDeveloper {
    public void codeJavascript() {
        System.out.println("I am coding javascript!");
    }
}

class Project {
    private FrontendDeveloper frontendDeveloper = new FrontendDeveloper();
    private BackendDeveloper backendDeveloper = new BackendDeveloper();

    public void start() {
        // here project directly depends on methods of concrete implementation
        frontendDeveloper.codeJavascript();
        backendDeveloper.codeJava();
    }
}

public class DIPViolation {
    public static void main(String[] args) {
        Project project = new Project();
        project.start();
    }
}
