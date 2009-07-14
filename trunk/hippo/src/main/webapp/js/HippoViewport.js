
Ext.namespace('Hippo');

Hippo.HippoViewport = Ext.extend(Ext.Viewport, {
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'viewport'
			
			,items: [//{
				//region: 'north'
				//,height: 30
				//,xtype: 'experimenttoolpanel'
			//},
			new Hippo.panel.ExperimentToolPanel({region: 'north', height: 30}),
			{
				region: 'west',
				collapsible: true,
				width: 300,
				minWidth: 200,
				maxWidth: 400,
				autoScroll: true,
				split: true,
				layout: 'accordion',
				items: [{
					title: 'Experiments'
					,xtype: 'experimentlistpanel'
				}
				,{
					title: 'Settings'
					,xtype: 'experimentsettingspanel'
				}]
			},
			{
				region: 'center'
				,xtype: 'experimenttabpanel'
			}]
		});
		
		
		Hippo.HippoViewport.superclass.initComponent.apply(this, arguments);
		
		//this.relayEvents(this.items[0], ['newexperiment']);
		//this.on('newexperiment', function(){alert('viewport');}, this);
	}
	
});

Ext.reg('hippoviewport', Hippo.HippoViewport);



