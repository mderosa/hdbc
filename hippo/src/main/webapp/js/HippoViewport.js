
Ext.namespace('Hippo');

Hippo.HippoViewport = Ext.extend(Ext.Viewport, {
	layout: 'border',
	getToolPanel: function() {
		var toolPanel = this.getComponent(0);
		if (toolPanel == null || toolPanel.xtype != 'panel.ExperimentToolPanel') {
			throw new Error('the component has not been initialized or is misconfigured');
		}
		return toolPanel;
	}
	,addNewExperiment: function(data) {
		var store = this.getComponent('west').getComponent('experimentGrid').getStore();
		var gridRecord = Ext.data.Record.create(
				Hippo.grids.FieldDefinitionFactory.createFieldDef('experimentGrid')
			);
		var newdata = new gridRecord({uid: data.uid, title: data.title, purpose: data.purpose});
		store.add(newdata);
	}
	,initComponent: function() {
		Ext.apply(this, {
			id: 'viewport'
			
			,items: [{
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
		
		this.relayEvents(this.getToolPanel(), ['newexperiment']);
		this.on('newexperiment', function(){
				var dlg = new Hippo.dialog.NewExperimentDlg();
				this.relayEvents(dlg, ['newexperimentsaved']);
				dlg.show();
			}, this);
		this.on('newexperimentsaved', function(rsp, dlg) {
				this.addNewExperiment(rsp.experiment);
				dlg.destroy();
			}, this);
	}
	
});

Ext.reg('hippoviewport', Hippo.HippoViewport);



