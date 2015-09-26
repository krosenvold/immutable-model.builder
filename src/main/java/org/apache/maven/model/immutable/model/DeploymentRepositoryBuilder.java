package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class DeploymentRepositoryBuilder
    implements ItemBuilder<ImmDeploymentRepository>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public ImmDeploymentRepository build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String uniqueVersion = null;
        String id = null;
        String layout = null;
        String url = null;
        String name = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "uniqueVersion":
                            uniqueVersion = leafBuilder.build( node );
                            break;
                        case "id":
                            id = leafBuilder.build( node );
                            break;
                        case "name":
                            name = leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "layout":
                            layout = leafBuilder.build( node );
                            break;
                    }
            }
        }

        return new ImmDeploymentRepository( id, name, url, layout, uniqueVersion );
    }
}