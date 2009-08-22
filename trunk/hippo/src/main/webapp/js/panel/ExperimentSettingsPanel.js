
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentSettingsPanel = Ext.extend(Ext.form.FormPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'settings'
			,items: [{
				fieldLabel: 'show active',
				name: 'active',
				checked: true,
				xtype: 'checkbox'
			},
			{
				fieldLabel: 'show this weeks',
				name: 'thisweeks',
				xtype: 'checkbox'
			}]		
		});
		
		Hippo.panel.ExperimentSettingsPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimentsettingspanel', Hippo.panel.ExperimentSettingsPanel);