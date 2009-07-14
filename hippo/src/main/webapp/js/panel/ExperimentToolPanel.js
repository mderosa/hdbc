
Ext.namespace('Hippo');

Hippo.ExperimentToolPanel = Ext.extend(Ext.Toolbar, {
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				text: 'new experiment'
			}]
		});
		
		Hippo.ExperimentToolPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimenttoolpanel', Hippo.ExperimentToolPanel);
