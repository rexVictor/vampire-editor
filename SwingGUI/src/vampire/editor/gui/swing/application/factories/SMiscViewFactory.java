package vampire.editor.gui.swing.application.factories;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.SMiscView;
import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.view.factories.BloodpoolViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.HealthViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.MeritViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.MiscViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.MiscViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;
import vampire.editor.plugin.api.view.sheet.HealthView;
import vampire.editor.plugin.api.view.sheet.MeritView;
import vampire.editor.plugin.api.view.sheet.MiscView;

public class SMiscViewFactory implements MiscViewFactory{
	
	private final List<MiscViewFactoryModule> modules = new LinkedList<>();
	
	private final BloodpoolViewFactory bloodpoolViewFactory;
	
	private final HealthViewFactory healthViewFactory;
	
	private final MeritViewFactory meritViewFactory;
	
	public SMiscViewFactory(BloodpoolViewFactory bloodpoolViewFactory,
			HealthViewFactory healthViewFactory,
			MeritViewFactory meritViewFactory) {
		super();
		this.bloodpoolViewFactory = bloodpoolViewFactory;
		this.healthViewFactory = healthViewFactory;
		this.meritViewFactory = meritViewFactory;
	}

	@Override
	public void add(MiscViewFactoryModule module) {
		modules.add(module);
	}

	@Override
	public MiscView build(ModelToViewModelMapperAPI mapper, BloodPoolAPI bloodPool,
			HealthAPI health, MeritsAPI merits, MeritsAPI flaws) {
		for (MiscViewFactoryModule module : modules){
			module.processInitial(bloodPool, health, merits, flaws, mapper);
		}
		BloodPoolView bloodPoolView = bloodpoolViewFactory.build(mapper, bloodPool);
		HealthView healthView = healthViewFactory.build(mapper, health);
		MeritView meritView = meritViewFactory.build(mapper, merits);
		MeritView flawView = meritViewFactory.build(mapper, flaws);
		MiscView view = new SMiscView(bloodPoolView, healthView, meritView, flawView);
		for (MiscViewFactoryModule module : modules){
			module.processFinal(bloodPool, health, merits, flaws, mapper, view);
		}
		return view;
	}

}
