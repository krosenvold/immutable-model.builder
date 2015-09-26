package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class BuilderBuild
{

    private final GenericListBuilder<ImmPlugin> pluginsBuilder =
        new GenericListBuilder( "plugin", new PluginBuilder() );

    private final PluginManagementBuilder pluginManagementBuilder = new PluginManagementBuilder( pluginsBuilder );

    public ImmBuild build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<ImmPlugin> plugins = null;
        List<ImmPlugin> pluginManagement = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "plugins":
                            plugins = pluginsBuilder.build( node );
                            break;

                        case "pluginManagement":
                            pluginManagement = pluginManagementBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );

                    }
            }
        }
        return new ImmBuild( plugins, pluginManagement );

    }
}
