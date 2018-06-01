package erc.manager;

import erc.manager.ERC_ModelLoadManager.ModelOptions;

public class ERC_ModelLoadPlan {

	private String ModelName;
	private String TextureName;
	private String IconName;
	private ModelOptions Option = new ModelOptions();
	
	public String getModelName() {return ModelName;}
	public String getTextureName() {return TextureName;}
	public String getIconName() {return IconName;}
	public ModelOptions getOption() {return Option;}
	
	/*
	 * �������B �Œ�����f���t�@�C���A�e�N�X�`���t�@�C���A���݃t�@�C���̎w��͕K�{�ł��B
	 */
	public ERC_ModelLoadPlan(String ObjName, String TextureName, String IconName)
	{
		this.ModelName = ObjName;
		this.TextureName = TextureName;
		this.IconName = IconName;
		Option.setSeatNum(1);
	}
	
	/*
	 * �ԗ��̊�{�ݒ���s���܂��B�@length:�㑱�R�[�X�^�[��ڑ�����Ԋu�@width:�R�[�X�^�[�S�̂̕��@height:�R�[�X�^�[�S�̂̍����@canRide:�R�[�X�^�[�ɏ��邩�ǂ����̃t���O
	 * width,height�̓R�[�X�^�[�S�̂𕢂����Ƃ��ł��邭�炢�̃T�C�Y���w�肵�Ă��������B
	 */
	public boolean setCoasterMainData(float length, float width, float height, boolean canRide)
	{
		Option.Length = length;
		Option.Width = width;
		Option.Height = height;
		Option.canRide = canRide;
		return true;
	}
	
	/*
	 * ���Ȑ��̎w����s���܂��B 
	 */
	public boolean setSeatNum(int num)
	{
		if(num < 1)return false;
		Option.setSeatNum(num);
		return true;
	}
	
	/*
	 * �w��̔ԍ��̍��Ȃ̈ʒu�ݒ���s���܂��B�@index:���Ȕԍ�[1-�ݒ肵����] x:�������@y:���������@z:�i�s�����@rotation:���Ȃ̉�]��(degree)
	 */
	public boolean setSeatOffset(int index, float offsetX, float offsetY, float offsetZ)
	{
		if(index < 0 || index >= Option.SeatNum)return false;
		Option.offsetX[index] = offsetX;
		Option.offsetY[index] = offsetY;
		Option.offsetZ[index] = offsetZ;
		return true;
	}
	
	/*
	 * �w��̔ԍ��̍��Ȃ̉�]�ʐݒ���s���܂��B�@index:���Ȕԍ�[1-�ݒ肵����]�@rotX:�i�s�������̉�]�ʁ@rotY:�������̉�]�ʁ@rotZ:�������̉�]��
	 * ��]�ʂ̒P�ʂ͌ʓx�@�iradian�j�ł��B
	 * ��]�̓K�p����Z>Y>X�ł��B
	 */
	public boolean setSeatRotation(int index, float rotX, float rotY, float rotZ)
	{
		if(index < 0 || index >= Option.SeatNum)return false;
		Option.rotX[index] = rotX;
		Option.rotY[index] = rotY;
		Option.rotZ[index] = rotZ;
		return true;
	}
	
	/*
	 * �w��̔ԍ��̍��Ȃ̓����蔻��ݒ���s���܂��B�@index:���Ȕԍ�[1-�ݒ肵����]�@size:���ȓ����蔻��̗����̂̃T�C�Y
	 * ���Ȃɍ���Ƃ��A�A���R�[�X�^�[��ڑ�����Ƃ��ɂ��̓����蔻�肪�g���܂��B
	 */
	public boolean setSeatSize(int index, float size)
	{
		if(index < 0 || index >= Option.SeatNum)return false;
		Option.size[index] = size;
		return true;
	}
}
