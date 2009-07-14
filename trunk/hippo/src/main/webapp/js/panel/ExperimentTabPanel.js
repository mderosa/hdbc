
Ext.namespace('Hippo');

Hippo.ExperimentTabPanel = new Ext.extend(Ext.TabPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			activeItem: 0,
			items: [{
				title: 'Overview',
				html: 'put overview data here'
			},
			{
				title: 'Data',
				html: 'put raw data here'
			},
			{
				title: 'Graphics',
				html: 'put graphic analysis here'
			}]
		});
		
		Hippo.ExperimentTabPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimenttabpanel', Hippo.ExperimentTabPanel);