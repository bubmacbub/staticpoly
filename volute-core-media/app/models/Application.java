package models;


import play.data.validation.Constraints;

public class Application implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Constraints.Required
    private String name;

    @Constraints.Required
    private String version;

    private String appInfo;
    
    public String getAppInfo() {
		return appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}
	public String getName() {
        return name;
    }
    public void setName(String n) {
        this.name = n;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String v) {
        this.version = v;
    }
}