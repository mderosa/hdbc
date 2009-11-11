Ext.namespace('Hippo.form');

Hippo.form.ExperimentDetailForm = Ext.extend(Ext.form.FormPanel, {

	setFormFields: function(data) {
		if (!data) {
			throw new Error("Can not set form fields with a null object");
		}
		this.items.each(function(item) {
			var name = item.getName();
			if (data[name] == undefined) {
				//skip
			} else if (data[name] == null) {
				item.setValue("");
			} else {
				item.setValue(data[name]);
			}
		});
	},
 
    initComponent: function(){
        // Called during component initialization
 
        // Config object has already been applied to 'this' so properties can 
        // be overriden here or new properties (e.g. items, tools, buttons) 
        // can be added, eg:
        // create config
		var config = {
			title: 'Details',
			bodyStyle: 'padding-left: 10px; padding-right: 10px',
			defaults: {width: '90%'},
    		items: [
    			{
    				xtype: 'numberfield', 
    				hidden: true,
    				name: 'uid'
    			},
    			{
    				xtype: 'textfield',
    				name: 'title',
    				fieldLabel: 'title'
    			},
    			{
    				xtype: 'textfield',
    				name: 'purpose',
    				fieldLabel: 'purpose'
    			},
    			{
    				xtype: 'combo',
    				name: 'type',
    				fieldLabel: 'type',
    				width: '100'
    			},
    			{
    				xtype: 'textarea',
    				name: 'method',
    				fieldLabel: 'method',
    				height: '150'
    			},
    			{
    				xtype: 'textarea',
    				name: 'conclusion',
    				fieldLabel: 'conclusion',
    				height: '150'
    			},
    			{
    				xtype: 'checkbox',
    				name: 'active',
    				fieldLabel: 'active'
    			}
    			
    		],
    		buttons: [
    			{
    				xtype: 'button',
    				text: 'save'
    			}
    		],
    		buttonAlign: 'center'
		}; // eo config object
 
		// apply config
		Ext.apply(this, Ext.apply(this.initialConfig, config));

        // Before parent code
 
        // Call parent (required)
        Hippo.form.ExperimentDetailForm.superclass.initComponent.apply(this, arguments);
 
        // After parent code
        // e.g. install event handlers on rendered component
    },
 
    // Override other inherited methods 
    onRender: function(){
 
        // Before parent code
 
        // Call parent (required)
        Hippo.form.ExperimentDetailForm.superclass.onRender.apply(this, arguments);
 
        // After parent code
 
    }
});
 
// register xtype to allow for lazy initialization
Ext.reg('form.ExperimentDetailForm', Hippo.form.ExperimentDetailForm);