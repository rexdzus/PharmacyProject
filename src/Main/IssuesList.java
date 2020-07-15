package Main;

public class IssuesList {
    String userName;
    String severity;
    String comments;
    String myDoctor;

    public IssuesList(String userName, String severity, String comments,
                      String myDoctor) {
        this.userName = userName;
        this.severity = severity;
        this.comments = comments;
        this.myDoctor = myDoctor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMyDoctor() {
        return myDoctor;
    }

    public void setMyDcotor(String myDoctor) {
        this.myDoctor = myDoctor;
    }
}
