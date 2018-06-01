package erc.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import erc._core.ERC_Logger;
import erc.entity.ERC_EntityCoaster;
import erc.entity.ERC_EntityCoasterConnector;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

//sideonly server
public class ERC_ManagerCoasterLoad {
	
	//�eMap�p
	static class ParentConstructConnection{
		ERC_EntityCoaster parent;
		Map<Integer, ERC_EntityCoasterConnector> childrenMap;
		ParentConstructConnection(ERC_EntityCoaster p)
		{
			parent = p;
			childrenMap = new TreeMap<Integer, ERC_EntityCoasterConnector>();
		}
		public void addChildren(ERC_EntityCoasterConnector child, int idx)
		{
			childrenMap.put(idx, child);
		}
	}
	
	//�qMap�p
	static class ConnectorCoasterCounter{
		ERC_EntityCoasterConnector coaster;
		int counter = 40; //�e�����[�h����邩�ǂ����҂��ԁitick�j
		public ConnectorCoasterCounter(ERC_EntityCoasterConnector c){coaster = c;}
	}
	
	static Map<UUID, ParentConstructConnection> parentMap = new HashMap<UUID, ParentConstructConnection>();
	static Map<Integer, ConnectorCoasterCounter>childMap = new HashMap<Integer, ConnectorCoasterCounter>();
	
	//�e�����[�h���ꂽ�炱���ɓo�^
	public static void registerParentCoaster(ERC_EntityCoaster parent)
	{
		if(parent.connectNum==0)return; //�q���Ȃ����Ă��Ȃ�������o�^���Ȃ�
		parentMap.put(parent.getUniqueID(), new ParentConstructConnection(parent));
//		ERC_Logger.info("register manager: parent, num:"+parent.connectNum + " ... parentid:"+parent.getUniqueID().toString());
	}
	//�q�����[�h���ꂽ�炱���ɓo�^
	public static void registerChildCoaster(ERC_EntityCoasterConnector child)
	{
		childMap.put(child.getEntityId(), new ConnectorCoasterCounter(child));
	}
	//�q���e�͂��Ȃ����Ɗm�F���ɗ���
	public static boolean searchParent(int childid, int idx, UUID parentid)
	{
		//�����e���������Ă���(Map�ɓ����Ă��Ȃ�)�q����̗v���͋p��
		if(childMap.get(childid)==null)return false;
		ParentConstructConnection parent = parentMap.get(parentid);
		if(parent == null)
		{
			//�e��������񂩂���
			ERC_Logger.info("find parent false");
			ConnectorCoasterCounter ccc = childMap.get(childid);
//			ERC_Logger.info("CoasterManager countdown:"+(ccc.counter-1)+" parentid:"+parentid.toString());
			if(--ccc.counter <= 0)
			{
				ccc.coaster.killCoaster(); // ��莞�Ԗ���������A�C�e����
				childMap.remove(childid);
			}
			return false;
		}
		else
		{
			// �e����������J�E���g�f�N�������g�Ǝq�̓o�^
//			ERC_Logger.info("find parent, parentID:"+parent.parent.getEntityId()+"childID:"+childid+"num:"+parent.parent.connectNum+">"+(parent.parent.connectNum-1));
			parent.childrenMap.put(idx, childMap.get(childid).coaster);
			if(--parent.parent.connectNum<=0)
			{
				parent.parent.clearConnectCoaster();
				for(Integer i : parent.childrenMap.keySet())
				{
					ERC_EntityCoasterConnector c = parent.childrenMap.get(i);
		            parent.parent.connectionCoaster(c);
		        }
				parentMap.remove(parentid); //�e�ɑS���q��������I���
//				ERC_Logger.info("connect all children");
			}
			// �q�͍폜
			childMap.remove(childid);
		}
		return true;
	}
	
	
	public static Entity SearchEntityWithUUID(World world, UUID uuid)
	{
		@SuppressWarnings("unchecked")
		List<Entity> elist = world.getLoadedEntityList();
		for(Entity e :elist)
		{
			if(e.getUniqueID().equals(uuid))
				return e;
		}
		return null;
	}
}
