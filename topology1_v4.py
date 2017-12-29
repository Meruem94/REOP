"""Custom topology example
TOPOLOGY FOR REOP PROJET
"""
from mininet.topo import Topo
class MyTopo( Topo ):
	"Simple topology example."
	def __init__( self ):
		"TOPOLOGY 1"
		# Initialize topology
		Topo.__init__( self )
		# Add hosts and switches
		lefttopHost1 = self.addHost( 'h1', ip='10.0.2.1' )
		lefttopHost2 = self.addHost( 'h2', ip='10.0.2.2' )
		righttopHost1 = self.addHost( 'h3', ip='10.0.2.3' )
		righttopHost2 = self.addHost( 'h4', ip='10.0.2.4' )
		leftbotHost1 = self.addHost( 'h5', ip='10.0.2.5' )
		leftbotHost2 = self.addHost( 'h6', ip='10.0.2.6' )	
		rightbotHost1 = self.addHost( 'h7', ip='10.0.2.7' )
		rightbotHost2 = self.addHost( 'h8', ip='10.0.2.8' )
		lefttopSwitch = self.addSwitch( 's1', listenPort=6634, ip='10.0.2.10' )
		righttopSwitch = self.addSwitch( 's2', listenPort=6634, ip='10.0.2.20' )
		leftbotSwitch = self.addSwitch( 's3', listenPort=6634, ip='10.0.2.30' )
		rightbotSwitch = self.addSwitch( 's4', listenPort=6634, ip='10.0.2.40' )
		# Add links
		self.addLink( lefttopHost1, lefttopSwitch )
		self.addLink( lefttopHost2, lefttopSwitch )
		self.addLink( righttopHost1, righttopSwitch )
		self.addLink( righttopHost2, righttopSwitch )
		self.addLink( leftbotHost1, leftbotSwitch )
		self.addLink( leftbotHost2, leftbotSwitch )
		self.addLink( rightbotHost1, rightbotSwitch )
		self.addLink( rightbotHost2, rightbotSwitch )	
		self.addLink( lefttopSwitch, righttopSwitch )
		self.addLink( lefttopSwitch, leftbotSwitch )
		self.addLink( rightbotSwitch, righttopSwitch )
		self.addLink( rightbotSwitch, leftbotSwitch )
		self.addLink( lefttopSwitch, rightbotSwitch )
		self.addLink( righttopSwitch, leftbotSwitch )
topos = { 'mytopo': ( lambda: MyTopo() ) }
