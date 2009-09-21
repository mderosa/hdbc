
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentTabPanel = new Ext.extend(Ext.TabPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			activeItem: 0
		});
		
		Hippo.panel.ExperimentTabPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('panel.ExperimentTabPanel', Hippo.panel.ExperimentTabPanel);