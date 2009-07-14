
Ext.namespace('Hippo');

Hippo.ExperimentSettingsPanel = Ext.extend(Ext.form.FormPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
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
		
		Hippo.ExperimentSettingsPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimentsettingspanel', Hippo.ExperimentSettingsPanel);