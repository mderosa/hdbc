
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentTabPanel = new Ext.extend(Ext.TabPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'expTab'
			,activeItem: 0
			,items: [{
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
		
		Hippo.panel.ExperimentTabPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimenttabpanel', Hippo.panel.ExperimentTabPanel);