package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.FreebieConfig;

public class FSheetController implements FreebieChangeListener{
	
	private final List<FreebieChangeListener> listeners = new LinkedList<>();
	
	private final FCatController attributes;
	
	private final FCatController abilities;
	
	private final FSingleSubCatController disciplines;
	
	private final FSingleSubCatController backgrounds;
	
	private final FSingleSubCatController virtues;
	
	private final FDependentController path;
	
	private final FDependentController willpower;
	
	private int freebies;
	
	private FreebieConfig config;

	public FSheetController(FCatController attributes,
			FCatController abilities, FSingleSubCatController disciplines,
			FSingleSubCatController backgrounds, FSingleSubCatController virtues,
			FDependentController path, FDependentController willpower,
			FMeritController meritController,
			FMeritController flawController,
			FreebieConfig config) {
		super();
		this.abilities = abilities;
		this.attributes = attributes;
		this.backgrounds = backgrounds;
		this.disciplines = disciplines;
		this.path = path;
		this.virtues = virtues;
		this.willpower = willpower;
		this.config = config;
		this.freebies = config.getFreebies();
		
		
		attributes.add(this);
		abilities.add(this);
		backgrounds.addListener(this);
		disciplines.addListener(this);
		virtues.addListener(this);
		path.addListener(this);
		willpower.addListener(this);
		meritController.addListener(this);
		flawController.addListener(this);
	}
	
	public void setConfig(FreebieConfig config){
		this.config = config;
		attributes.setConfig(config.getAttributes());
		abilities.setConfig(config.getAbilities());
		backgrounds.setConfig(config.getBackgrounds());
		disciplines.setConfig(config.getDisciplines());
		virtues.setConfig(config.getVirtues());
		path.setConfig(config.getPath());
		willpower.setConfig(config.getWillpower());
		
		this.freebies = config.getFreebies();
		freebies -= attributes.getFreebies();
		freebies -= abilities.getFreebies();
		freebies -= backgrounds.getFreebies();
		freebies -= disciplines.getFreebies();
		freebies -= virtues.getFreebies();
		freebies -= path.getFreebies();
		freebies -= willpower.getFreebies();
	}

	@Override
	public void freebieChanged(FreebieChangeEvent event) {
		freebies += event.getFormerFreebies();
		freebies -= event.getNewFreebies();
		FreebieChangeEvent e = new FreebieChangeEvent(freebies, freebies);
		for (FreebieChangeListener l : listeners){
			l.freebieChanged(e);
		}
	}
	
	public FreebieConfig getConfig(){
		return config;
	}
	
	public int getFreebies(){
		return freebies;
	}
	
	public void addListener(FreebieChangeListener listener){
		listeners.add(listener);
	}

}
