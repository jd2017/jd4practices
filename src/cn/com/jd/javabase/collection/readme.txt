Collection	JDK 1.2出现
 |-List：有序的集合，可以有重复的元素。因为该集合体系有索引。 
	|—ArrayList:　底层的数组数据结构. 查询速度很快；增、删、改慢。线程不同步
	|—LinkedList:	底层为链表结构。 增、删、改速度快。查 速度慢。
	|—Vector:	底层是数组数据结构。JDK 1.0出现	 线程同步，被ArrayList替代。
		ArrayList与Vector：可变长度数组。默认都为10；ArrayList为(旧容量*3/2+1)增加，Vector:100%增加；
	List 操作数据
	 * ArrayList:
		特有的方法：凡是操作角标的方法都是该体系特有的方法。
			增：
				add(index,element);
				addAll(index,Collection);
			删：
				remove(index);
			改：
				set(index,element);
			查：
				get(index);
				subList(start,end);
				listIterator();
		List集合特有的迭代器。 ListIterator 是Iterator的子接口。

		在迭代时，不可以通过集合对象的方法操作集合中的元素。
		因为会发生 ConcurrentModificationException 异常。
		
		所以，在迭代器时，只能用迭代器的方法操作元素，
		可是Iterator方法是有限的，只能对元素进行判断，取出，删去操作。
		
		如果想要其他的操作如：添加、修改等，需要使用其子接口：ListIterator。
		该接口只能通过List集合的 ListIterator方法获取。
		
	 |-Set：无序的集合，一个不包含重复元素的 collection。
		|-HashSet：底层数据结构是哈希表		线程为非同步
			其底层 HashMap 实例的默认初始容量是 16，加载因子是 0.75。
			hashSet是如何保证元素唯一性的呢？
			是通过元素的两个方法，hashcode和equals来完成。
			如果元素的HashCode值相同，才会判断equals是否为true。
			如果元素的hashcode值不同，不会调用equals方法。
	
			注意：HashSet对于判断元素是否存在及删除等操作，依赖方法为
				hashCode和equals方法。
			而ArrayList 判断元素只依赖于 equals 方法。
			原因是：数据结构不同。
		|-ThreeSet：可以对Set集合中的元素进行排序(ASCII码表进行排序)。	线程不同步的。 JDK1.2
			使用元素的自然顺序对元素进行排序，
			或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
	
		Set:一个不包含重复元素的 collection。更确切地讲，set 不包含满足 e1.equals(e2) 的元素对 e1 和 e2，
			并且最多包含一个 null 元素.
		
		|-Set：无序的集合，一个不包含重复元素的 collection。
			|-HashSet：底层数据结构是哈希表.		线程为不同步的。
				hashSet是如何保证元素唯一性的呢？
				是通过元素的两个方法，hashCode和equals来完成。
				如果元素的和hashCode值相同，才会判断equals是否为true。
				如果元素的hashCode值不同，不会调用equals方法。
	
				注意：HashSet对于判断元素是否存在及删除等操作，依赖方法为
					hashCode和equals方法。
				而ArrayList 判断元素只依赖于 equals 方法。
				原因是：数据结构不同。
	
			|-ThreeSet：可以对Set集合中的元素进行排序(ASCII码表进行排序)。	线程不同步的。 JDK1.2
				使用元素的自然顺序对元素进行排序，
				或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
				comparable(compareTo(Object obj))/comparator(compare(Object obj1,Object obj2))
		Set 底层是使用了Map集合。
		Hash表是按照 哈希值来存储，存储无序的。当哈希值相同(位置)时，判断是否是同一个对象(equals)。
		取出时，只有一种方式，为迭代器。
		Set 集合的功能和Collection 一致。
	
	Map
	|-Hashtable：底层哈希表数据结构；不允许 null作为键或值。同步线程。JDK1.0  效率低。
	|-HashMap：底层哈希表数据结构；允许 null作为键或值。线程不同步。JDK1.2 效率高
	|-TreeMap：底层二叉树数据结构；线程不同步；重要的是：可用于map集合中的键进行排序。
	Set 底层是使用了Map集合。
	Map 集合：
		存储键值对，键的唯一性，每个键最多只能映射到一个值。可以多键一个值。
		Map 集合：
		存储键值对，键的唯一性，每个键最多只能映射到一个值。可以多键一个值。
	1，添加：
		V put(K key, V value) 
	          将指定的值与此映射中的指定键关联（可选操作）。 若相同的Key，则会覆盖原来Value
		void putAll(Map<? extends K,? extends V> m) 
	          从指定映射中将所有映射关系复制到此映射中（可选操作）。 
	2，删除：
		 void clear() 
	          从此映射中移除所有映射关系（可选操作）。 
		 V remove(Object key) 
	          如果存在一个键的映射关系，则将其从此映射中移除（可选操作）。 
	3，判断：
		boolean containsKey(Object key) 
	          如果此映射包含指定键的映射关系，则返回 true。 
		boolean containsValue(Object value) 
	          如果此映射将一个或多个键映射到指定值，则返回 true。 
		boolean equals(Object o) 
	          比较指定的对象与此映射是否相等。
		boolean isEmpty() 
	          如果此映射未包含键-值映射关系，则返回 true。
	4，获取：
		Set<K> keySet() 
	          返回此映射中包含的键的 Set 视图。 
		Set<Map.Entry<K,V>> entrySet() 
	          返回此映射中包含的映射关系的 Set 视图。
		V get(Object key) 
	          返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。 
		int size() 
	          返回此映射中的键-值映射关系数。 
	          
	接口 Map.Entry<K,V>：
		映射项（键-值对）。
	Map.Entry 其实Entry也是一个接口，他是Map接口中的一个内部接口。