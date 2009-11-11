
Ext.namespace('Hippo');

/**
 * The central viewport and event locus for the application
 * <p>
 * Note that we want the id field set in the configuration passed to extend() as this is necessary
 * for the other components to find the viewport during initialization and register events
 * @class Hippo.HippoViewport
 * @extends Ext.Viewport
 */
Hippo.HippoViewport = Ext.extend(Ext.Viewport, {
	layout: 'border',
	id: 'viewport',
	
	getToolPanel: function() {
		var toolPanel = this.getComponent(0);
		if (toolPanel == null || toolPanel.xtype != 'panel.ExperimentToolPanel') {
			throw new Error('the component has not been initialized or is misconfigured');
		}
		return toolPanel;
	}

	,initComponent: function() {
		Ext.apply(this, {

			items: [{
				region: 'north'
				,id: 'north'
				,height: 30
				,xtype: 'panel.ExperimentToolPanel'
			}
			,{
				region: 'west'
				,id: 'west'
				,collapsible: true,
				width: 300,
				minWidth: 200,
				maxWidth: 400,
				autoScroll: true,
				split: true,
				layout: 'accordion',
				items: [{
					title: 'Experiments'
					,xtype: 'grids.ExperimentGrid'
				}
				,{
					title: 'Settings'
					,xtype: 'panel.ExperimentSettingsPanel'
				}]
			},
			{
				region: 'center',
				xtype: 'panel.ExperimentTabPanel',
				items: [
					{
						xtype: 'form.ExperimentDetailForm'
					},
					{
						title: 'Data'
					},
					{
						title: 'Graphics'
					}
				]
			}]
		});
		
		Hippo.HippoViewport.superclass.initComponent.apply(this, arguments);
		this.addEvents({
			"on_newExperimentSaved": true,
			"on_experimentSelected": true
		})
	}
	
});

Ext.reg('hippoviewport', Hippo.HippoViewport);



