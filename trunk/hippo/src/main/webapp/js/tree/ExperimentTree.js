
Ext.namespace('Hippo.tree');

Hippo.tree.ExperimentTree = Ext.extend(Ext.tree.TreePanel, {
    // Prototype Defaults, can be overridden by user's config object
    propA: 1,
 
    initComponent: function(){
        // Called during component initialization
 
        // Config object has already been applied to 'this' so properties can 
        // be overriden here or new properties (e.g. items, tools, buttons) 
        // can be added, eg:
        // create config
		var config = {
    		// put your config here
		}; // eo config object
 
		// apply config
		Ext.apply(this, Ext.apply(this.initialConfig, config));

        // Before parent code
 
        // Call parent (required)
        Hippo.tree.ExperimentTree.superclass.initComponent.apply(this, arguments);
 
        // After parent code
        // e.g. install event handlers on rendered component
    },
 
    // Override other inherited methods 
    onRender: function(){
 
        // Before parent code
 
        // Call parent (required)
        Hippo.tree.ExperimentTree.superclass.onRender.apply(this, arguments);
 
        // After parent code
 
    }
});
 
// register xtype to allow for lazy initialization
Ext.reg('tree.ExperimentTree', Hippo.tree.ExperimentTree);