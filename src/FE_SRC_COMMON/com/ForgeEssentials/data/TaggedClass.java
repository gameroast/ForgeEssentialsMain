package com.ForgeEssentials.data;

import java.util.HashMap;
import java.util.Map.Entry;

public class TaggedClass
{
	public class SavedField
	{
		public String name;
		public Object value;
		public Class type;

		public SavedField()
		{
		}

		public SavedField(String name, Object value)
		{
			this.name = name;
			this.value = value;
			type = value.getClass();
		}
		
		@Override
		public String toString()
		{
			return "{"+name+", "+type+", "+value+"}";
		}
	}

	public Class type;
	protected SavedField uniqueKey;
	protected HashMap<String, SavedField> TaggedMembers;

	public TaggedClass()
	{
		TaggedMembers = new HashMap<String, SavedField>();
	}

	public void addField(SavedField field)
	{
		TaggedMembers.put(field.name, field);
	}

	public Object getFieldValue(String name)
	{
		Object value = null;
		if (uniqueKey != null && (!uniqueKey.name.endsWith("()") && uniqueKey.name.equals(name)))
		{
			value = uniqueKey.value;
		}
		else
		{
			if (TaggedMembers.containsKey(name))
			{
				value = TaggedMembers.get(name).value;
			}
		}

		return value;
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder("{");
		s.append("type=").append(type).append(", ");
		s.append("unique=").append(uniqueKey).append(", ");
		
		s.append("[");
		for (Entry<String, SavedField> e : TaggedMembers.entrySet())
		{
			s.append(e.getKey()).append("=").append(e.getValue()).append(", ");
		}
		s.replace(s.length()-2, s.length(), "]");
		
		s.append("}");
		
		return s.toString();
	}
}
