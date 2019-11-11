import java.util.List;

interface Developer {
    public void code();
}

class BackendDeveloper implements Developer {
    @Override
    public void code() {
        this.codeJava();
    }

    public void codeJava() {
        System.out.println("I am coding java!");
    }
}

class FrontendDeveloper implements Developer {
    @Override
    public void code() {
        this.codeJavascript();
    }

    public void codeJavascript() {
        System.out.println("I am coding javascript!");
    }
}

class Project {
    private List<Developer> developers;

    public Project(List<Developer> developers) {
        this.developers = developers;
    }

    public void start() {
        for (Developer developer : this.developers) {
            developer.code();
        }
    }
}

public class DIP {
    public static void main(String[] args) {
        Project project = new Project(List.of(new FrontendDeveloper(), new BackendDeveloper()));
        project.start();
    }
}
