package vampire.editor.copyright.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Project {
	
	private String name;
	
	private String license;
	
	private Path licensetext;
	
	private String copyright;
	
	private String link;
	
	private String actualLicense; 
	
	private List<Project> libs = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicensetext() {
		if (actualLicense == null){
			StringBuilder sb = new StringBuilder();
			sb.append("<html>"); //$NON-NLS-1$
			String input = null;
			try (BufferedReader reader = Files.newBufferedReader(licensetext, Charset.defaultCharset())){
				while ((input = reader.readLine()) != null){
					input = input.replace("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
					input = input.replace(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$
					sb.append(input);
					sb.append("<br>"); //$NON-NLS-1$
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb.append("</html>"); //$NON-NLS-1$
			this.actualLicense = sb.toString();
		}
		return actualLicense;
	}

	public void setLicensetext(Path licensetext) {
		this.licensetext = licensetext;
	}
	
	@JsonSetter("licensetext")
	public void setLicensetext(String licensetext){
		setLicensetext(Paths.get(licensetext));
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", license=" + license //$NON-NLS-1$ //$NON-NLS-2$
				+ ", licensetext=" + licensetext + ", copyright=" + copyright //$NON-NLS-1$ //$NON-NLS-2$
				+ ", link=" + link + ", libs=" + libs + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Project> getLibs() {
		return libs;
	}

	public void setLibs(List<Project> libs) {
		this.libs = libs;
	}
}
